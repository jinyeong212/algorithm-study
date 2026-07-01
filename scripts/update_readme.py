from pathlib import Path
from datetime import datetime, timezone, timedelta
import re

ROOT = Path(__file__).resolve().parents[1]
README = ROOT / "README.md"
CHANGED_FILES = ROOT / "changed_files.txt"

KST = timezone(timedelta(hours=9))


def today_kst() -> str:
    return datetime.now(KST).strftime("%Y-%m-%d")


def parse_problem_dir(problem_dir_name: str):
    """
    예:
    '42586. 기능개발' -> ('42586', '기능개발')
    '12901. 2016년' -> ('12901', '2016년')
    """
    problem_dir_name = problem_dir_name.replace("\u2005", " ")
    problem_dir_name = problem_dir_name.replace("\u00a0", " ")
    problem_dir_name = re.sub(r"\s+", " ", problem_dir_name).strip()

    match = re.match(r"^(\d+)\.\s*(.+)$", problem_dir_name)

    if not match:
        return "-", problem_dir_name

    problem_id = match.group(1)
    title = match.group(2)

    return problem_id, title


def parse_path(path: str):
    """
    예상 경로:
    임진영/programmers/lv2/42586. 기능개발/Solution.java
    """
    parts = Path(path).parts

    if len(parts) < 5:
        return None

    name = parts[0]
    platform = parts[1]
    level = parts[2]
    problem_dir = parts[3]

    # 코드 파일만 반응하게 하고 싶으면 확장자 필터
    suffix = Path(path).suffix.lower()
    if suffix not in [".java", ".py", ".cpp", ".cc", ".cxx", ".js", ".ts", ".sql", ".kt"]:
        return None

    problem_id, title = parse_problem_dir(problem_dir)

    return {
        "name": name,
        "platform": platform,
        "level": level,
        "problem_id": problem_id,
        "title": title,
    }


def collect_changed_problems():
    if not CHANGED_FILES.exists():
        print("changed_files.txt not found")
        return []

    problems = []
    seen = set()

    for line in CHANGED_FILES.read_text(encoding="utf-8").splitlines():
        path = line.strip()

        parsed = parse_path(path)

        if parsed is None:
            continue

        key = (
            parsed["name"],
            parsed["platform"],
            parsed["level"],
            parsed["problem_id"],
            parsed["title"],
        )

        if key in seen:
            continue

        seen.add(key)
        problems.append(parsed)

    return problems


def ensure_readme_base(content: str) -> str:
    if not content.strip():
        content = "# Algorithm Study\n"

    if "## 오늘의 풀이 기록" not in content:
        content += "\n\n## 오늘의 풀이 기록\n"

    return content


def make_row(problem):
    return (
        f"| {problem['name']} "
        f"| {problem['platform']} "
        f"| {problem['level']} "
        f"| {problem['problem_id']} "
        f"| {problem['title']} |"
    )


def remove_duplicate_rows(content: str) -> str:
    lines = content.splitlines()
    result = []
    seen_rows = set()

    for line in lines:
        if line.startswith("| 임진영 |") or line.startswith("| 이용준 |"):
            if line in seen_rows:
                continue
            seen_rows.add(line)

        result.append(line)

    return "\n".join(result) + "\n"


def update_readme():
    problems = collect_changed_problems()

    if not problems:
        print("No solved problems detected")
        return

    date = today_kst()

    content = README.read_text(encoding="utf-8") if README.exists() else ""
    content = ensure_readme_base(content)

    date_header = f"### {date}"
    table_header = (
        "| 이름 | 플랫폼 | 난이도 | 문제 번호 | 문제명 |\n"
        "|---|---|---|---|---|"
    )

    rows = "\n".join(make_row(problem) for problem in problems)

    if date_header in content:
        pattern = (
            rf"(### {re.escape(date)}\n\n"
            rf"\| 이름 \| 플랫폼 \| 난이도 \| 문제 번호 \| 문제명 \|\n"
            rf"\|---\|---\|---\|---\|---\|\n)"
        )

        content = re.sub(
            pattern,
            r"\1" + rows + "\n",
            content,
            count=1,
        )
    else:
        today_section = (
            f"{date_header}\n\n"
            f"{table_header}\n"
            f"{rows}\n"
        )

        content = content.replace(
            "## 오늘의 풀이 기록\n",
            f"## 오늘의 풀이 기록\n\n{today_section}\n",
            1,
        )

    content = remove_duplicate_rows(content)
    README.write_text(content, encoding="utf-8")

    print(f"README updated for {date}")


if __name__ == "__main__":
    update_readme()
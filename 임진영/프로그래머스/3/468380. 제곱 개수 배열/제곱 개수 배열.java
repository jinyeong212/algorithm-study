class Solution {
    Section[] sections;
    long sum, cnt, length;
    long start, end;
    
    private class Section {
        long start, end;
        int value, idx;
        
        Section(long start, long end, int value, int idx) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.idx = idx;
        }
        
        @Override
        public String toString() {
            return "[start] : " + start
                    + " [end] : " + end
                    + " [value] : " + value + "\n";
        }
    }
    
    public long[] solution(int[] arr, long l, long r) {
        init(arr, l - 1, r - 1);
        
        getCnt();
        
        return new long[] {sum, cnt};
    }
    
    private void getCnt() {
        long totalLength = sections[sections.length - 1].end + 1;
        
        start = 0;
        end = length - 1;
        
        long curSum = getSum(start, end);
        
        int leftIdx = 0;
        int rightIdx = findSection(end);
        
        if(curSum == sum)
            cnt++;
        
        while(end + 1 < totalLength) {
            while(start > sections[leftIdx].end)
                leftIdx++;
            
            int inIdx = rightIdx;
            
            if(end == sections[rightIdx].end)
                inIdx++;
            
            Section left = sections[leftIdx];
            Section right = sections[inIdx];
            
            long leftRemain = left.end - start + 1;
            long rightRemain = right.end - end;
            
            long jump = Math.min(leftRemain, rightRemain);
            
            long maxJump = totalLength - 1 - end;
            jump = Math.min(jump, maxJump);
            
            long diff = (long) right.value - left.value;
            
            if(diff == 0) {
                if(curSum == sum)
                    cnt += jump;
            }else {
                long target = sum - curSum;
                
                if(target % diff == 0) {
                    long move = target / diff;
                    
                    if(move >= 1 && move <= jump)
                        cnt++;
                }
            }
            
            curSum += diff * jump;
            start += jump;
            end += jump;
            
            while(leftIdx + 1 < sections.length
                    && start > sections[leftIdx].end)
                leftIdx++;
            
            while(rightIdx + 1 < sections.length
                    && end > sections[rightIdx].end)
                rightIdx++;
        }
    }
    
    private void init(int[] arr, long l, long r) {
        sections = new Section[arr.length];
        
        cnt = 0;
        sum = 0;
        
        length = r - l + 1;
        
        long flag = 0;
        
        for(int i = 0; i < arr.length; i++) {
            sections[i] = getSection(flag, arr[i], i);
            flag += arr[i];
        }
        
        sum = getSum(l, r);
    }
    
    private long getSum(long l, long r) {
        long result = 0;
        
        for(int i = 0; i < sections.length; i++) {
            Section section = sections[i];
            
            if(section.end < l)
                continue;
            
            if(section.start > r)
                break;
            
            long sectionStart = Math.max(l, section.start);
            long sectionEnd = Math.min(r, section.end);
            
            result += (long) section.value
                    * (sectionEnd - sectionStart + 1);
        }
        
        return result;
    }
    
    private int findSection(long position) {
        int left = 0;
        int right = sections.length - 1;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            Section section = sections[mid];
            
            if(position < section.start) {
                right = mid - 1;
            }else if(position > section.end) {
                left = mid + 1;
            }else {
                return mid;
            }
        }
        
        return -1;
    }
    
    private Section getSection(long start, int value, int idx) {
        return new Section(start, start + value - 1, value, idx);
    }
}
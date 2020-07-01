public class FindRepeatNumber {
    // 第一种 先排序 再判断 时间复杂度为 o(nlogn)
    static class SolutionA {
        public static void qsort(int nums[], int l, int size) {
            if (l >= size) {
                return;
            }
            int i = l, j = size - 1;
            int key = nums[l];
            while (i < j) {
                while (i < j && nums[j] > key) { --j; }
                nums[i] = nums[j];
                while (i < j && nums[i] <= key) { ++i; }
                nums[j] = nums[i];
            }
            nums[i] = key;
            qsort(nums, l, i-1);
            qsort(nums, i+1, size);
        }
        public static int findRepeat(int []nums) {
            nums = nums.clone();
            qsort(nums, 0, nums.length);
            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] == nums[i-1]) {
                    return nums[i];
                }
            }
            return -1;
        }
    }

    // 第二种 交换判断
    static class SolutionB {
        public static int findRepeat(int nums[]) {
            for (int i=0; i < nums.length; ++i) {
                while (nums[i] != i) {
                    if (nums[i] == nums[nums[i]]) {
                        return nums[i];
                    }
                    int t = nums[i];
                    nums[i] = nums[t];
                    nums[t] = t;
                }
            }
            return -1;
        }
    }

    // 第三种 对值域二分
    static class SolutionC {
        public static int findRepeat(int nums[]) {
            int start = 1, end = nums.length - 1;
            while (start <= end) {
                int middle = ((end - start) / 2) + start;
                int count = countRange(nums, start, middle);
                if (start == end) {
                    if (count > 1) {
                        return start;
                    } else {
                        break;
                    }
                }
                if (count > (middle - start + 1)) {
                    end = middle;
                } else {
                    start = middle + 1;
                }
            }
            return -1;
        }
        public static int countRange(int nums[], int start, int end) {
            int count = 0;
            for (int i : nums) {
                if (i >= start && i <= end) {
                    ++count;
                }
            }
            return count;
        }
    }

    public static void main (String args[]) {
        int l[] = {0, 7, 2, 3, 4, 5, 3, 6, 1};

        System.out.println(SolutionA.findRepeat(l));
        System.out.println(SolutionB.findRepeat(l));
        System.out.println(SolutionC.findRepeat(l));
    }
}

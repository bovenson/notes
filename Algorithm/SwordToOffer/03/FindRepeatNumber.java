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

        }
    }
    public static void main (String args[]) {
        int l[] = {-1, 10, 4, 1, 2, -3, 4, 2, 0};
        SolutionA.qsort(l, 0, l.length);

        for (int i : l) {
            System.out.println(i);
        }
    }
}

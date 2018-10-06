class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        arr = []
        need = set()
        sum = 0
        pos_1 = 0
        pos_2 = 0
        len_1 = len(nums1)
        len_2 = len(nums2)
        len_arr = len_1 + len_2
        
        need.add(len_arr/2)
        if len_arr % 2 == 0:
            need.add(len_arr/2-1)
            
        need_len = len(need)
            
        for i in range(len_arr):
            _cur = 0
            if pos_1 < len_1 and pos_2 < len_2:
                if nums1[pos_1] < nums2[pos_2]:
                    _cur = nums1[pos_1]
                    pos_1 += 1
                else:
                    _cur = nums2[pos_2]
                    pos_2 += 1
            elif pos_1 < len_1:
                _cur = nums1[pos_1]
                pos_1 += 1
            else:
                _cur = nums2[pos_2]
                pos_2 += 1
            if i in need:
                need.remove(i)
                sum += _cur
                
                if len(need) == 0:
                    break

        return 1.0 * sum / need_len

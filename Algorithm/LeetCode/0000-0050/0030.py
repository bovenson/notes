#!/bin/python3
# coding: utf-8

"""
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-19 19:38"


# class Solution(object):
#     def findSubstring(self, s, words):
#         """
#         :type s: str
#         :type words: List[str]
#         :rtype: List[int]
#         """
#         if len(words) == 0 or len(words[0]) == 0:
#             return [0]
#
#         s_words = set(words)
#         s_t_words = set()
#         word_len = len(words[0])
#         res = []
#         start = 0
#         i = 0
#         start_str = ''
#         while i < len(s):
#             t_str = s[i:i+word_len]
#             if len(s) - i > word_len and t_str in s_words and t_str not in s_t_words:
#                 if len(s_t_words) == 0:
#                     start = i
#                     # print 'start ', i
#                     start_str = t_str
#                 s_t_words.add(t_str)
#             else:
#                 s_t_words.clear()
#                 if len(s) - i > word_len and t_str in s_words:
#                     s_t_words.add(t_str)
#                     start = i
#                     start_str = t_str
#
#             if len(s_t_words) == len(words):
#                 res.append(start)
#                 s_t_words.remove(start_str)
#                 start += word_len
#                 start_str = s[start:start+word_len]
#             i += word_len
#
#         return res

class SolutionA(object):
    def findSubstring(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """
        import copy
        t_collect = copy.copy(words)
        res = []
        word_len = len(words[0])
        i = 0
        queue = []
        while i <= len(s) - word_len:
            t_str = s[i:i+word_len]
            print t_str
            if t_str in t_collect:
                queue.append(t_str)
                t_collect.remove(t_str)
            elif t_str in words:
                while len(queue) > 0:
                    if queue[0] == t_str:
                        queue.append(t_str)
                        queue.pop(0)
                        break
                    else:
                        t_collect.append(queue[0])
                        queue.pop(0)
            else:
                t_collect = copy.copy(words)
                queue = []

            if len(t_collect) == 0:
                t_collect.append(queue[0])
                queue.pop(0)
                res.append(i - len(words) * word_len + word_len)
            i += word_len
            print queue
            print t_collect
            print ''
        return res


class Solution(object):
    def findSubstring(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """


if __name__ == "__main__":
    # print Solution().findSubstring("barfoothefoobarman", ["foo", "bar"])
    # print Solution().findSubstring("barfoofoobarthefoobarman", ["bar", "foo", "the"])
    print Solution().findSubstring("wordgoodgoodgoodbestword", ["word","good","best","good"])


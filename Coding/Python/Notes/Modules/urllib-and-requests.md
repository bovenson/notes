# urllib-and-requests

## 代理

```python
# codding: utf-8
# python 3.6+
import traceback
import urllib
import urllib.request
import urllib.parse
import time
from urllib import request

import requests

proxy_ips = [
'220.179.7.249:8080',
]


class ProxyManager(object):
    @staticmethod
    def get_proxy_ip():
        if len(proxy_ips) == 0:
            # 下载代理ip
            ips = ''
            # ips = str(HtmlDownloader.download('http://api.xicidaili.com/free2016.txt', use_proxy=False), encoding='utf-8')
            # ips = str(HtmlDownloader.download('http://tvp.daxiangdaili.com/ip/?tid=555709422560593&num=10', use_proxy=False), encoding='utf-8')
            for ip in ips.split():
                proxy_ips.append(ip)
        if len(proxy_ips) != 0:
            ip = proxy_ips.pop(0)
            proxy_ips.append(ip)
            return ip
        else:
            return None

    @staticmethod
    def remove_ip(ip):
        try:
            proxy_ips.remove(ip)
        except:
            pass


class HtmlDownloader(object):

    @staticmethod
    def download(url, data=None, delay=None, use_proxy=True):
        if use_proxy:
            for _i in range(10):
                _proxy_ip = ProxyManager.get_proxy_ip()
                try:
                    print('使用代理ip:', _proxy_ip)
                    return HtmlDownloader.download_solve(url, data, delay, _proxy_ip)
                except Exception as e:
                    print('下载出错:', e)
                    if use_proxy:
                        print('尝试更换代理')
                        ProxyManager.remove_ip(_proxy_ip)
        else:
            return HtmlDownloader.download_solve(url, data, delay, None)

    @staticmethod
    def download_solve(url, data=None, delay=None, proxy_ip=None):
        """
        下载页面
        :param proxy_ip:
        :param url:
        :param data:
        :param delay: 下载延迟, 单位: 秒
        :return:
        """
        if url is None:
            return None

        if delay:
            try:
                print('sleep ', delay, ' seconds')
                delay = int(delay)
                time.sleep(delay)
                print('sleep over')
            except Exception as e:
                print(e)
                traceback.print_exc()

        # if data is not None:
        #     data = urllib.parse.urlencode(data).encode(encoding="utf-8")
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/31.0.1650.63 Safari/537.36'}

        if proxy_ip:
            # proxy_handler = urllib.request.ProxyHandler({'http': proxy_ip})
            # req = urllib.request.Request(url, data, headers, proxy_handler)
            # res = urllib.request.urlopen(req, timeout=15)

            # print("使用代理IP下载页面:", proxy_ip)
            # proxy_handler = urllib.request.ProxyHandler({'http': proxy_ip})
            # # proxy_auth_handler = urllib.request.ProxyBasicAuthHandler()
            # # proxy_auth_handler.add_password('realm', '123.123.2123.123', 'user', 'password')
            # opener = urllib.request.build_opener(proxy_handler)
            # request.install_opener(opener)
            # res = opener.open(url, data=data)

            # proxy_handler = urllib.request.ProxyHandler({'http': 'http://' + proxy_ip + '/'})
            # opener = urllib.request.build_opener(proxy_handler)
            # res = opener.open(url, data=data)
            req = requests.get(url, params=data, headers=headers, proxies={'http', proxy_ip})
            return req.content
        else:
            return requests.get(url, params=data, headers=headers).content
            # req = urllib.request.Request(url, data, headers)
            # res = urllib.request.urlopen(req, timeout=15)
            # if res.getcode() != 200:
            #     print("下载页面 %s 失败." % res.url)
            #     return None
            # print("下载页面 %s 成功." % res.url)
            # return res.read()

    @staticmethod
    def download_response(url, data=None, delay=None, use_proxy=False):
        """
        下载页面
        :param url: 
        :param data: 
        :param delay: 下载延迟, 单位: 秒
        :return: 
        """
        if url is None:
            return None

        if delay:
            try:
                print('sleep ', delay, ' seconds')
                delay = int(delay)
                time.sleep(delay)
                print('sleep over')
            except Exception as e:
                print(e)
                traceback.print_exc()

        if data is not None:
            data = urllib.parse.urlencode(data).encode(encoding="utf-8")
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/31.0.1650.63 Safari/537.36'}
        if use_proxy:
            proxy_handler = urllib.request.ProxyHandler({'http': ProxyManager.get_proxy_ip()})
            req = urllib.request.Request(url, data, headers, proxy_handler)
        else:
            req = urllib.request.Request(url, data, headers)
        res = urllib.request.urlopen(req, timeout=15)
        if res.getcode() != 200:
            print("下载页面 %s 失败." % res.url)
            return None
        print("下载页面 %s 成功." % res.url)
        return res


if __name__ == "__main__":
    print(str(HtmlDownloader.download('http://tvp.daxiangdaili.com/ip/?tid=555709422560593&num=10', use_proxy=False), encoding='utf-8'))
    # for i in range(100):
    #     print(ProxyManager.get_proxy_ip())
    # downloader = HtmlDownloader()
    # print(str(downloader.download("http://toutiao.io/"), encoding='utf8'))
    pass

```


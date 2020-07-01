#!/bin/python
import logging

LOG_FORMAT = "[%(asctime)s %(funcName)s() %(lineno)i]\t[%(levelname)s] %(message)s"
DATE_FORMAT = "%m/%d/%Y %H:%M:%S %p"

logging.basicConfig(level=logging.DEBUG, filename="out.log", format=LOG_FORMAT, datefmt=DATE_FORMAT)

logging.debug("debug log")
logging.info("info log")
logging.warning("warning log")

def log_f():
    logging.error("error log")

log_f()

from collections import deque
from concurrent.futures import ThreadPoolExecutor

# generalized stack, queue
# threadsafe , memory efficient, ~ O(1) pop, push 
# deque's push, pop,... are thread-safe

def Task(n, dq_ref: deque):
    print(n, id(dq_ref))
    dq_ref.extend(range(n))

# known recepie in docs
# last n records/linrd from file : tail in unix
# moving average
# round robin
# remove nth element 

def task_process():
    executor = ThreadPoolExecutor(3)
    arr = [2000, 64, 1680, 299, 456, 3434]
    dq = deque()
    for i in arr:
        executor.submit(Task, i, dq)
    executor.shutdown(wait=True)
    print(dq)

task_process()
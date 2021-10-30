# known recepie in docs
# LRU cache impl
# OrderedDictv2 remember last insertion as updated
# order in case of duplication in keys

from collections import OrderedDict

base = 'wubalubadubdub'
a = OrderedDict.fromkeys(base)

print(a)
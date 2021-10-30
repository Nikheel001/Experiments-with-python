from collections import defaultdict

a = defaultdict(int)
b = {}
# b = {i:i for i in range(25)}

a[26]+=7

print(dir(defaultdict))

# b[26]+=7

# try :
#     b[26]+=7
# except KeyError:
#     pass

print(a, b)
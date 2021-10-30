from collections import Counter

abc = {"word1":4 , "word2":5}

a = Counter(abc)

arr =["word1", "word2", "word3", "word2"]

b = Counter(arr)

# print(dir(Counter))

# fromkeys NotImplemented
print(a+b)
print(a-b)
print(a&b)
print(a|b)

print(a.most_common(1))

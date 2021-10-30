from collections import namedtuple
from csv import reader
from json import dump

header = namedtuple('header', 'x1 x2 x3 x4 x5 x6')

# to console
# def csv_to_json(csvfile):
#     with open(csvfile, 'r') as fptr:
#         for row in reader(fptr):
#             yield header._make(row)

# doc = [i._asdict() for i in csv_to_json('sample.csv')]
# del doc[0]
# print(doc)

# to file
def csv_to_json(csvfile, jsonfile):
    with open(csvfile, 'r') as csv_ptr:
        with open(jsonfile, 'w') as json_ptr:
            dump([header._make(row)._asdict() for row in reader(csv_ptr)], json_ptr, indent=2)

csv_to_json('sample.csv', 'out.json')
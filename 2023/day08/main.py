from collections import defaultdict
import math

directionsMap = defaultdict(list)
directions_mapping = {"R": "1", "L": "0"}

def read_input(file: str = "input.txt"):
  global sequence, directions
  with open(file) as file:
    sequence, *[directions] = file.read().split("\n\n")

  sequence = sequence.translate(str.maketrans(directions_mapping))
  for el in directions.strip().split("\n"):
    x, y = el.split(" = ")
    directionsMap[x.strip()] = [e.strip() for e in y[1:-1].split(", ")]

def aToZSteps(end):
  steps = 0
  while True:
    for i in sequence:
      direction = directionsMap[end][int(i)]
      end = direction
      steps += 1
      if (end[-1] == 'Z'):
        return steps

def partOne():
  end = 'AAA'
  steps = 0

  while end != 'ZZZ':
    for i in sequence:
      direction = directionsMap[end][int(i)]
      end = direction
      steps += 1

  return steps

def partTwo():
  ends = [x for x in directionsMap.keys() if x[-1] == 'A']
  LCMs = [aToZSteps(end) for end in ends]

  return math.lcm(*LCMs)

if __name__ == "__main__":
  read_input()
  print(partOne())
  print(partTwo())

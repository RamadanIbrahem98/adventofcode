from collections import defaultdict

directionsMap = defaultdict(list)
directions_mapping = {"R": "1", "L": "0"}

def gcd(a, b):
  while b:
    a, b = b, a%b
  return a

def LCM(nums):
  lcm = nums[0]
  for i in nums[1:]:
    lcm = lcm*i//gcd(lcm, i)

  return lcm

def read_input(file: str = "input.txt"):
  global sequence, directions
  with open(file) as file:
    sequence, *[directions] = file.read().split("\n\n")

def partOne():
  global sequence
  sequence = sequence.translate(str.maketrans(directions_mapping))
  for el in directions.strip().split("\n"):
    x, y = el.split(" = ")
    directionsMap[x.strip()] = [e.strip() for e in y[1:-1].split(", ")]

  end = 'AAA'
  steps = 0

  while end != 'ZZZ':
    for i in sequence:
      direction = directionsMap[end][int(i)]
      end = direction
      steps += 1

  return steps

def partTwo():
  global sequence
  sequence = sequence.translate(str.maketrans(directions_mapping))
  for el in directions.strip().split("\n"):
    x, y = el.split(" = ")
    directionsMap[x.strip()] = [e.strip() for e in y[1:-1].split(", ")]

  ends = [x for x in directionsMap.keys() if x[-1] == 'A']
  steps = 0
  LCMs = [0 for i in range(len(ends))]
  foundZ = [1 for i in range(len(ends))]

  while True:
    for i in sequence:
      for j, x in enumerate(ends):
        if (foundZ[j] == 0):
          continue
        ends[j] = directionsMap[x][int(i)]
        LCMs[j] = (LCMs[j] + 1) if foundZ[j] == 1 else LCMs[j]
        foundZ[j] = 0 if ends[j][-1] == 'Z' else 1
      steps += 1
      if 1 not in foundZ:
        return LCM(LCMs)

if __name__ == "__main__":
  read_input()
  print(partOne())
  print(partTwo())

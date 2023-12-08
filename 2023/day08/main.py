from collections import defaultdict

directionsMap = defaultdict(list)
directions_mapping = {"R": "1", "L": "0"}

with open("input.txt") as file:
  sequence, *[directions] = file.read().split("\n\n")

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

print(steps)

package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	s "strings"
)

func ReadLines() ([]int64, []int64, error) {
	var scanner *bufio.Scanner
	var left []int64
	var right []int64

	if len(os.Args) > 1 {
		filePath := os.Args[1]
		file, err := os.Open(filePath)
		if err != nil {
			fmt.Printf("Error opening file: %v\n", err)
			return nil, nil, err
		}
		defer file.Close()
		scanner = bufio.NewScanner(file)
	} else {
		scanner = bufio.NewScanner(os.Stdin)
	}

	for scanner.Scan() {
		line := scanner.Text()
		if line == "" {
			continue
		}

		vals := s.Split(line, "   ")

		leftVal, _ := strconv.ParseInt(vals[0], 10, 64)
		rightVal, _ := strconv.ParseInt(vals[1], 10, 64)

		left = append(left, leftVal)
		right = append(right, rightVal)
	}

	if err := scanner.Err(); err != nil {
		fmt.Printf("Error reading input: %v\n", err)
		return nil, nil, err
	}

	return left, right, nil
}

func problemOne(left, right []int64) int64 {
	sort.Slice(left, func(i, j int) bool {
		return left[i] < left[j]
	})
	sort.Slice(right, func(i, j int) bool {
		return right[i] < right[j]
	})

	acc := 0.0

	for i := range len(left) {
		acc += math.Abs(float64(left[i]) - float64(right[i]))
	}

	return int64(acc)
}

func problemTwo(left, right []int64) int64 {
	m := make(map[int64]int64)
	var acc int64

	for _, val := range right {
		m[val] += 1
	}

	for _, val := range left {
		acc += (val * m[val])
	}

	return acc

}

func main() {
	left, right, err := ReadLines()

	if err != nil {
		fmt.Printf("Error : %v\n", err)
		os.Exit(1)
	}

	fmt.Printf("%d\n", problemOne(left, right))
	fmt.Printf("%d\n", problemTwo(left, right))

}

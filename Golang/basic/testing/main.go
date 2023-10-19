package main

import (
	"regexp"
	"strings"
)

func IsPalindrome(text string) bool {
	clean_text := cleanString(text)
	var i, j int
	rune := []rune(clean_text)
	for i = 0; i < len(rune)/2; i++ {
		j = len(rune) - 1 - i
		if string(rune[i]) != string(rune[j]) {
			return false
		}
	}
	return true
}

func cleanString(text string) string {
	clean_text := strings.ToLower(text)
	clean_text = strings.Join(strings.Fields(clean_text), "") // Remove spaces
	regex, _ := regexp.Compile(`[^\p{L}\p{N} ]+`)             // Regular expression for alphanumeric only characters
	return regex.ReplaceAllString(clean_text, "")
}
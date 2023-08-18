* Xor of all subarrays of an array and xoring all the xors obtained.
```
O(n) approach
Array of length - 1 => finalxor=arr[0]
Array of length - 2 => [a,b] => finalxor = a^b^(a^b) = 0 //total number of each element is even
Array of length - 3 => [a,b,c] => finalxor = a^b^c^(a^b)^(a^b^c)^(b^c) = a^c
Array of length - 4 => [a,b,c,d] => finalxor = a^b^c^d^(a^b)^(a^b^c)^(a^b^c^d)^(b^c)^(b^c^d)^(c^d) = 0
... and so on
Something noticeable happens, in an array of even length, finalxor=0, since the expression of finalxor consists of even number of each element of array.
We know, 
a^a=0
a^0=a
Also, in an array of odd length,(0-indexed talking of)
element at odd occurence had odd number of frequency in final xor while those at even index had even number of frequence and reduces to zero in finale.
```
Brute Force - O(n^3)
Find all subarrays and go on xoring them

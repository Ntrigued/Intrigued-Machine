# Adds two positive integers
#
# #####INSTRUCTIONS#############
# change A to the first number
# and B to the second number
# the result of A+B will be printed to the ouput
# at position 20 20
# ##############################
#
# set operate box to 4
	ADD 11
	ADD 11
	ADD 11
	ADD 11
#
# changes value of box 64 to A
LABEL loop1
	ADD 64
	BOOL 64 A
	JUMP loop1
#
# changes value of box 65 to B
LABEL loop2
	ADD 65
	BOOL 65 B
	JUMP loop2
#
# zeroes out operate box
	ZERO 11
#
# performs (box 65) = (box 65) + (box 64)
	ADD 11
	OPERATE 64 65
#
# prints value of box 65 to output at 20 20
# then changes value of box 65 to 0
	ADD 11
 	OPERATE 65 0 20 20 1

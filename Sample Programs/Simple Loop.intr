LABEL START
# set BOOL to case 4
	ADD 11
	ADD 11
	ADD 11
	ADD 11
#
# prints from 1 to 9 in screen position 20 20
LABEL LOOP1
	ADD 66
	SET 20 20
	PRINT
	BOOL 66 9
	JUMP LOOP1
#
	ZERO 66
#
# prints from 1 to 9 in screen position 20 21
LABEL LOOP2
	ADD 66
	SET 20 21
	PRINT
	BOOL 66 9
	JUMP LOOP2
#
	ZERO 66
#
# prints from 1 to 9 in screen position 20 22
LABEL LOOP3
	ADD 66
	SET 20 22
	PRINT
	BOOL 66 9
	JUMP LOOP3
#
# waits 2 seconds, then clears screen
	ZERO 66
	WAIT 2000
	CLEAR
	PRINT

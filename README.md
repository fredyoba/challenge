PROVIDED SPECIFICATIONS:
# Connect Four

__Description__

Your mission, should you choose to accept it, is to implement a Connect Four game.
https://en.wikipedia.org/wiki/Connect_Four

1. The vertical board is composed of seven columns and six rows. Initially, all positions are empty.
2. Players introduce discs at the top of the columns. The disc falls to the bottom of the column if the column is empty. Future discs introduced to the same column will stack over previous ones.
3. It is a two-person game. Player 1 uses red ('R') and Player 2 uses green ('G'). Players take alternate turns, inserting one disc each time.
4. When no more discs can be inserted, the game finishes, and it is considered a draw.
5. If a player inserts a disc and connects more than three discs of his color in a straight vertical, horizontal or diagonal line, then that player wins.

__Sample Run__

````text
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |

Player 1 [RED] - choose column (1-7): 4
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | |R| | | |

Player 2 [GREEN] - choose column (1-7): 4
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | |G| | | |
| | | |R| | | |

Player 1 [RED] - choose column (1-7): 5
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | |G| | | |
| | | |R|R| | |

Player 2 [GREEN] - choose column (1-7): 5
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | |G|G| | |
| | | |R|R| | |

Player 1 [RED] - choose column (1-7): 3
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | |G|G| | |
| | |R|R|R| | |

Player 2 [GREEN] - choose column (1-7): 2
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | |G|G| | |
| |G|R|R|R| | |

Player 1 [RED] - choose column (1-7): 6
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | |G|G| | |
| |G|R|R|R|R| |

Player 1 [RED] wins!
````
disclaimer:
To follow the original game, i used the color yellow instead of green.

How to build:
1. cd CONNECT4 folder (go into the project folder)
2. use maven to build the sample. ie: mvn clean package
3. run the program. ie: java -jar target/connect4-1.0.jar
please contact me at fyoba@totalnihon.com for any questions
 
It's a springboot project run as a standalone application, this enable enhancements in the future, but since here we focus on the logic,
it is perfectly operational

Fred

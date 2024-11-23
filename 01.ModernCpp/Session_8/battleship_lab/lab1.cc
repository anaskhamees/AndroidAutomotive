#include<iostream>
#include<random>
#include<vector>
   


class battleship
{
	private:
    	
		int guessesNum;
		int maxGuesses;
		int locX;
		int locY;
	public:
		battleship(int MaxGuess,int rows,int cols,bool initial_value){
			maxGuesses=	MaxGuess;
			/*Initialize a 2D vector with the same value*/
    		std::vector<std::vector<bool>> board(rows, std::vector<bool>(cols, initial_value));

			/*Create a random device to seed the random number generator*/
    		std::random_device rd;

    		/* Step 2: Create a random number generator */
    		std::mt19937 gen(rd()); // Standard mersenne_twister_engine

    		/* Step 3: Define a uniform integer distribution in the range [0, 4] */
   			 std::uniform_int_distribution<> dis(0, 4);
   			 locX=dis(gen);
   			 locY=dis(gen);
   			 guessesNum=0;
   			 
		}	
		
	bool guess(int x,int y)
	{
		guessesNum++;

			if((x==locX)&&(y==locY))
			{
				std::cout<<"################################################\n";
				std::cout<<" Ture, you are correct !!\n";
				std::cout<<"################################################\n";
				return true;
			}
			else
			{
				std::cout<<"Wrong !! , you have "<<(maxGuesses-guessesNum)<<" guesses left,, take care \n";
			}
			
			if(x==locX)
			{
				std::cout<<"You are in the same row of battleship , stay on the same row !!\n";
			}
			else if(y==locY)
			{
				std::cout<<"You are in the same colomn of battleship , stay on the same colomn !!\n";
			}
		
		return false;
	}
	
	bool gameOver() const
	{
		if(guessesNum==maxGuesses)
		{
			return true;
		}
		
		return false;

	}

	int getGuesses() const
	{
		return guessesNum;
	}



~battleship()
{

}


};



int main()
{
	int maxGuess=5;
	int row=5;
	int col=5;
	int x=0;
	int y=0;
	bool initVal=false;
	battleship game1(maxGuess,row,col,false);
	
	while(1)
	{
		if(game1.getGuesses()>maxGuess)
		{
			std::cout<<"################################################\n";
			std::cout<<"Game over !!\n";
			std::cout<<"################################################\n";
			break;
		}
		else
		{
			std::cout<<"################################################\n";
			std::cout<<" Enter the X location   : ";
			std::cin>>x;
			std::cout<<" Enter the Y location   : ";
			std::cin>>y;
			std::cout<<"################################################\n";
			bool ret=game1.guess(x,y);
			if(ret==true)
			{
				break;
			}
		}
	}
	
	return 0;

}

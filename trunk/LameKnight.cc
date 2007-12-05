#include <iostream>
#include <cstring>

using namespace std;

class LameKnight
{
private:
	int a, b;
	int move1, move2;

	void init( int h, int w )
	{
		a = h > w ? h : w;
		b = h < w ? h : w;
		move1 = move2 = 0;
	}

	bool inside( int x, int y )
	{
		if ( x >0 && x <=a && y > 0 && y <=b )
			return true;
		else
			return false;
	}

	void smallMove( int x, int y, int cntMove, int sum )
	{
		if ( !inside(x, y) || cntMove > 3 )
			return ;
		if ( sum > move1 )
			move1 = sum;
		smallMove( x+1, y+2, cntMove+1, sum+1 );
		smallMove( x+2, y+1, cntMove+1, sum+1 );
		smallMove( x+2, y-1, cntMove+1, sum+1 );
		smallMove( x+1, y-2, cntMove+1, sum+1 );
	}
public:
	int maxCells(int height, int width)
	{
		init( height, width );
		smallMove( 1, 1, 0, 0 );

		a -= 5;
		if ( b > 2 )
			move2 += (a + 2);

		int ans = move1 > move2 ? move1 : move2;
		return ans + 1;
	}
};

int main()
{
	LameKnight lk;
	int h, w;
	while ( cin>>h>>w )
		cout<<lk.maxCells(h,w)<<endl;
return 0;
}


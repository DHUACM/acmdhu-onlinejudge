#include <iostream>
#include <string>
using namespace std;


class LuckyTicketSubstring
{
private:

public:
	int maxLength(string s)
	{
		int len = s.size();
		int ans = 0;
		int i, j, k;
		for ( i=0; i<len-1; i++ )
			for ( j=i+1; j<len; j++ )
			{
				if ( (j-i+1)%2 == 1 )
					continue;
				int sum1=0, sum2=0;
				for ( k=i; k<=(i+j)/2; k++ )
					sum1 += (int)(s[k]-'0');
				for ( ; k<=j; k++ )
					sum2 += (int)(s[k]-'0');
				if ( sum1 == sum2 ){
					if ( j-i+1 > ans )
						ans = j-i+1;
				}
			}
		return ans;
	}
};


int main()
{
	LuckyTicketSubstring lts;
	string s;
	while ( cin>>s )
	{
		cout<<lts.maxLength(s)<<endl;
	}
return 0;
}


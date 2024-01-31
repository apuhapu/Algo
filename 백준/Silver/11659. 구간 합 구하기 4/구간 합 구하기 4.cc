#include <bits/stdc++.h>
#define endl '\n'

using namespace std;


int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    
    int n, m;
    cin>>n>>m;
    
    int arr[n];
    for (int i=0; i<n; i++) {
        cin>>arr[i];
    }
    
    int dp[n+1];
    dp[0] = 0;
    for (int i=0; i<n; i++) {
        dp[i+1] = dp[i]+arr[i];
    }    
    
    while (m--) {
        int a, b;
        cin>>a>>b;
        cout<<dp[b]-dp[a-1]<<endl;
    }

}
#include <bits/stdc++.h> 
#define endl '\n'

using namespace std;


bool isOut(int r, int c, int i) {
    if (0<=r<i && 0<=c<i) {
        return true;
    } else {
        return false;
    }
}
int graphN[101][101];
int graphW[101][101]; // graphN : normal, graphW : weakness


int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int dr[4] = {1,-1,0,0};
    int dc[4] = {0,0,1,-1};
    int cnt_normal = 0, cnt_weakness = 0;
    
    int n;
    cin>>n;
    
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            char ch;
            cin>>ch;
            // r=1, g=2, b=3 / r,g=1, b=2
            if (ch == 'R') {
                graphN[i][j] = 1;
                graphW[i][j] = 1;
            } else if (ch == 'G') {
                graphN[i][j] = 2;
                graphW[i][j] = 1;
            } else if (ch == 'B') {
                graphN[i][j] = 3;
                graphW[i][j] = 2;
            }
        }
    }
    
    queue<pair<int, int>> q;
    int key;
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {    
            // 2. weakness
            if (graphW[i][j]!=0) {
                cnt_weakness++;
                q.push(make_pair(i,j));
                key = graphW[i][j];
                graphW[i][j] = 0;
                
                while (!q.empty()) {
                    int r = q.front().first;
                    int c = q.front().second;
                    q.pop();
                    for (int k=0; k<4; k++) {
                        int rr = r + dr[k];
                        int cc = c + dc[k];
                        if (isOut(rr,cc,n) && graphW[rr][cc]==key) {
                            graphW[rr][cc] = 0;
                            q.push(make_pair(rr,cc));
                        }
                    }
                    
                }
            }
        }
    }
    
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            
            // 1. noraml
            if (graphN[i][j]!=0) {
                cnt_normal++;
                q.push(make_pair(i,j));
                key = graphN[i][j];
                graphN[i][j] = 0;
                
                while (!q.empty()) {
                    int r = q.front().first;
                    int c = q.front().second;
                    q.pop();
                    for (int k=0; k<4; k++) {
                        int rr = r + dr[k];
                        int cc = c + dc[k];
                        if (isOut(rr,cc,n) && graphN[rr][cc]==key) {
                            graphN[rr][cc] = 0;
                            q.push(make_pair(rr,cc));
                        }
                    }
                } 
            }
        }
    }
    
    
    cout<<cnt_normal<<' '<<cnt_weakness;
}
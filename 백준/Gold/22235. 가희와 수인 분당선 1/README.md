# [Gold II] 가희와 수인 분당선 1 - 22235 

[문제 링크](https://www.acmicpc.net/problem/22235) 

### 성능 요약

메모리: 14472 KB, 시간: 136 ms

### 분류

다이나믹 프로그래밍, 구현

### 제출 일자

2024년 4월 17일 17:54:00

### 문제 설명

<p>하행은 서울을 시점으로 종점 방향으로 운행하는 방향을 의미합니다.</p>

<p>수인 분당선은 K209(청량리)역이 서울에 있고, K272(인천)역이 인천에 있으므로, <strong>K209역에서 K272역으로 가는 방향이 하행</strong>이 됩니다. </p>

<p> </p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/7fb10beb-8226-46cc-90c5-5594d6668aac/-/preview/"></p>

<p style="text-align: center;"><strong>[그림 1] 수인 분당선의 하행</strong></p>

<p>수인 분당선의 역 번호는 Kddd로 정의됩니다. 이 때 ddd는 209 이상 272 이하의 정수입니다. 이는 K209가 청량리역이고 K272가 인천역이기 때문입니다.</p>

<p>하행에서의 역 번호가 Kddd인 역의 다음 역은, 역 번호가 K272인 인천역을 제외하고, 정수 ddd + 1의 값이 DDD일 때 KDDD가 됩니다. </p>

<p>하행 열차는 아래와 같은 알고리즘으로 운행하게 됩니다.</p>

<ol>
	<li>시발역에서 <em>HH</em>시 <em>mm</em>분에 출발해서, 다음역으로 이동하게 됩니다.</li>
	<li>열차가 역에 도착했다면 20초 동안 역에 머무르게 됩니다. <strong>20초가 지난 후에는 문을 닫고 출발하게 됩니다.</strong></li>
	<li>만약에 종착역이라면 열차는 더 이상 운행하지 않습니다. 그렇지 않으면 다음역으로 이동한 후에 2번을 수행합니다.</li>
</ol>

<p>예를 들어 하행 열차가 역 번호가 K210인 역에서 운행을 시작해서, K233 역에서 운행을 종료한다고 해 보겠습니다.</p>

<p style="text-align: center;"> </p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/ed4543b8-b638-41ad-82b3-2c12bbaf29fe/-/preview/"></p>

<p style="text-align: center;"><strong>[그림 2] K233이 종착역인 하행 열차가 K232 역에 도착한 경우</strong></p>

<p>그림 2는 K210 역이 시발역이고, K233역이 종착역인 하행 열차가 K232 역에 도착한 상황과, K232 역에 도착하고 20초 후 상황을 도식화 한 그림입니다. </p>

<p>K232은 종착역이 아니므로, K232역에 도착하고 20초 후에는 K232 역을 출발해서 K233역 방향으로 가게 됩니다.</p>

<p style="text-align: center;"> </p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/4fde783d-bf27-483b-9671-9338a3e0a1c2/-/preview/"></p>

<p style="text-align: center;"><strong>[그림 3] K233이 종착역인 하행 열차가 K233 역에 도착한 경우</strong></p>

<p>그림 3은 K210 역이 시발역이고, K233역이 종착역인 하행 열차가 K233에 도착한 상황과, K233에 도착하고 나서 20초 후의 상황을 나타냅니다. </p>

<p><strong>K233은 종착역이므로 K233역에 도착한 열차는 K233에 도착하고 20초 후에 운행을 종료</strong>하게 됩니다. 운행이 종료된 열차는 그림에 표시되지 않습니다.</p>

<p>한 역에서 다음 역을 이동하는 시간은 표 1에 있는 경우를 제외하고 2분입니다.</p>

<table align="center" border="1" cellpadding="1" cellspacing="1" class="table table-bordered" style="width: 600px;">
	<tbody>
		<tr>
			<td colspan="2" rowspan="1" style="text-align: center;">구간</td>
			<td colspan="1" rowspan="2" style="text-align: center;">소요 시간</td>
			<td colspan="2" rowspan="1" style="text-align: center;">구간</td>
			<td colspan="1" rowspan="2" style="text-align: center;">소요시간</td>
		</tr>
		<tr>
			<td style="text-align: center;">현 역</td>
			<td style="text-align: center;">다음 역</td>
			<td style="text-align: center;">현 역</td>
			<td style="text-align: center;">다음 역</td>
		</tr>
		<tr>
			<td style="text-align: right;">K210</td>
			<td style="text-align: right;">K211</td>
			<td style="text-align: right;">3</td>
			<td style="text-align: right;">K220</td>
			<td style="text-align: right;">K221</td>
			<td style="text-align: right;">4</td>
		</tr>
		<tr>
			<td style="text-align: right;">K221</td>
			<td style="text-align: right;">K222</td>
			<td style="text-align: right;">4</td>
			<td style="text-align: right;">K222</td>
			<td style="text-align: right;">K223</td>
			<td style="text-align: right;">3</td>
		</tr>
		<tr>
			<td style="text-align: right;">K225</td>
			<td style="text-align: right;">K226</td>
			<td style="text-align: right;">3</td>
			<td style="text-align: right;">K238</td>
			<td style="text-align: right;">K239</td>
			<td style="text-align: right;">3</td>
		</tr>
		<tr>
			<td style="text-align: right;">K245</td>
			<td style="text-align: right;">K246</td>
			<td style="text-align: right;">4</td>
			<td style="text-align: right;">K247</td>
			<td style="text-align: right;">K248</td>
			<td style="text-align: right;">5</td>
		</tr>
		<tr>
			<td style="text-align: right;">K249</td>
			<td style="text-align: right;">K250</td>
			<td style="text-align: right;">4</td>
			<td style="text-align: right;">K250</td>
			<td style="text-align: right;">K251</td>
			<td style="text-align: right;">3</td>
		</tr>
		<tr>
			<td style="text-align: right;">K256</td>
			<td style="text-align: right;">K257</td>
			<td style="text-align: right;">3</td>
			<td style="text-align: right;">K266</td>
			<td style="text-align: right;">K267</td>
			<td style="text-align: right;">3</td>
		</tr>
	</tbody>
</table>

<p align="center"><strong>[표 1] 인접 역간 소요 시간이 3분 이상인 경우</strong></p>

<p>가희는 역 번호가 K225인 모란역에서 <strong>수인 분당선 열차만 타고</strong> 역 번호가 K272인 인천역에 가려고 합니다.</p>

<p>인천역에 가장 빠르게 도착했을 때 시각을 구해주세요.</p>

### 입력 

 <p>첫 번째 줄에는 가희가 모란역에 도착한 시각이 <em>HH:mm:ss</em> 형식으로 주어집니다. 항상 올바른 형식의 시각으로 주어집니다.</p>

<p>두 번째 줄에 수인 분당선 하행 열차의 운행 정보 갯수 <em>N</em>이 주어집니다.</p>

<p>세 번째 줄부터 N+2번째 줄까지, 열차가 운행을 시작하는 시발역의 역 번호, 운행을 마치는 종착역의 역 번호, 시발역 에서의 출발 시각이 주어집니다.</p>

<p>시발역에서의 출발 시각은 <em>HH:mm</em> 형식으로 주어집니다. 항상 올바른 형식의 시각으로 주어집니다.</p>

<p>예를 들어, 운행 정보가 K210 K272 11:20으로 주어지면, 이는 K210(왕십리)역을 11시 20분에 출발하여, K272(인천)역에서 운행을 종료하는 열차임을 의미합니다. </p>

### 출력 

 <p>인천역에 도착하는 가장 빠른 시각을 <em>HH:mm:ss </em>형식으로 출력합니다.</p>

<p>만약, <em>HH</em>, <em>mm</em>, <em>ss</em>가 10보다 작다면 앞에 0을 붙여서 출력하세요. 예를 들어, <strong>답이 9시 1분 0초라면 9:1:0이 아닌 09:01:00으로 출력해 주세요</strong>.</p>

<p><strong>금일 23:59:59 까지</strong> 수인 분당선만 타고 역 번호가 K272인 인천역에 도착할 수 없다면 -1을 출력합니다.</p>


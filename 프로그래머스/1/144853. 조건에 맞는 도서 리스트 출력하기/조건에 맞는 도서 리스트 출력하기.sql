-- 코드를 입력하세요
SELECT BOOK_ID, date_format(PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from BOOK
where substr(date_format(PUBLISHED_DATE, '%Y-%m-%d'),1,4) = 2021 and CATEGORY = '인문'
order by PUBLISHED_DATE
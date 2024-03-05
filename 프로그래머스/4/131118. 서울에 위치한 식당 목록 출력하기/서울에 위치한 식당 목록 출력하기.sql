-- 코드를 입력하세요
SELECT info.REST_ID, info.REST_NAME, info.FOOD_TYPE, info.FAVORITES, info.ADDRESS, review.SCORE
from REST_INFO as info, (
    select REST_ID, round(avg(REVIEW_SCORE),2) as SCORE
    from REST_REVIEW
    group by REST_ID
) as review
where info.REST_ID = review.REST_ID and info.ADDRESS like "서울%"
order by review.SCORE desc, info.FAVORITES desc
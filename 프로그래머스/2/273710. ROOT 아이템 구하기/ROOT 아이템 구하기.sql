-- 코드를 작성해주세요
select i.ITEM_ID, i.ITEM_NAME
from ITEM_INFO as i, ITEM_TREE as t
where i.ITEM_ID = t.ITEM_ID and t.PARENT_ITEM_ID is null

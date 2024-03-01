# 추가사항
## 1. API
- 댓글 생성
  - 일반 댓글
  - 대댓글
- 댓글 리스트 - 페이징, 검색조건
- 활성화, 비활성화
- 삭제

---

# ERD
![댓글_ERD_2024-03-01](https://github.com/Griotold/bankshop-griotold/assets/101307758/d23e1138-9e9b-4eae-893a-48db01288d91)

---
# 테스트시 id값 초기화하기

## 1. 개발환경과 테스트 환경 분리
- https://zircon-neptune-a7d.notion.site/id-7a6f757f77e24b74a716d2bd6b3666e4?pvs=4

## 2. 네이티브 쿼리로 id값 재설정
```java
@BeforeEach
void dataSet() {
    em.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
    em.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();

    for (int i = 0; i < 10; i++) {
        Category category
                = new Category("category" + i, "description" + i, i + 1, ActiveStatus.ACTIVE);
        categoryRepository.create(category);
    }
}
```
- 모든 테스트 시작 전에 id값을 재설정해주고 카테고리 10개를 새롭게 만든다.

---
# 컬렉션 조회 최적화 가이드
## 권장 순서
1. 엔티티 조회 방식으로 우선 접근
   1. ManyToOne, OneToOne은 fetch join으로 한방에 가져온다.

2. 컬렉션 최적화
   1. 페이징 필요X -> 페치 조인 사용하여 한방에 가져온다.
   2. 페이징 필요 -> hibernate.default_batch_fetch_size , @BatchSize 로 최적화
   
3. 엔티티 조회 방식으로 해결이 안되면 DTO 조회 방식 사용
4. DTO 조회 방식으로 해결이 안되면 NativeSQL or 스프링 JdbcTemplate
5. 성능을 더 끌어 올리려면 Redis 도입
## 목표 - N + 1 해결
![컬렉션조회최적화](https://github.com/Griotold/bankshop-griotold/assets/101307758/8d1530d0-b71e-4c3c-8240-5d070786d7eb)

## 스프링부트 3.1 - 하이버네이트 6.2 변경사항 - array_contains - 동적으로 in 쿼리가 늘어나는 현상 제어

- 스프링 부트 3.1 부터는 하이버네이트 6.2를 사용한다. 
- 하이버네이트 6.2 부터는 where in 대신에 array_contains 를 사용한다
- 성능 최적화
```sql
select ... where item.item_id in(?)
```

- 이러한 SQL을 실행할 때 데이터베이스는 SQL 구문을 이해하기 위해 SQL을 파싱하고 분석하는 등 여러가
지 복잡한 일을 처리해야 한다. 
- 그래서 성능을 최적화하기 위해 이미 실행된 SQL 구문은 파싱된 결과를 내부에 캐싱하고 있다 
- 그런데 where in 쿼리는 동적으로 데이터가 변하는 것을 넘어서 SQL 구문 자체가 변해버리는 문제가 발생한다. 
- 다음 예시는 in에 들어가는 데이터 숫자에 따라서 총 3개의 SQL구문이 생성된다.
```sql
where item.item_id in(?)
where item.item_id in(?,?)
where item.item_id in(?,?,?,?)
```
- SQL 입장에서는 ? 로 바인딩 되는 숫자 자체가 다르기 때문에 완전히 다른 SQL이다. 
- 따라서 총 3개의 SQL 구문이 만들어지고, 캐싱도 3개를 따로 해야한다. 
- 이렇게 되면 성능 관점에서 좋지않다. 
- array_contains 를 사용하면 이런 문제를 깔끔하게 해결할 수 있다. 
- 이 문법은 결과적으로 where in 과 동일하다.
- array_contains 은 왼쪽에 배열을 넣는데, 배열에 들어있는 숫자가 오른쪽(item_id)에 있다면 참이된다. 
- 예시) 다음 둘은 같다.
```sql
select ... where array_contains([1,2,3],item.item_id)
select ... item.item_id where in(1,2,3)
```
- 따라서 배열에 들어가는 데이터가 늘어도 SQL 구문 자체가 변하지 않는다. 
- ? 에는 배열 하나만 들어가면된다. 
- 이런 방법을 사용하면 앞서 이야기한 동적으로 늘어나는 SQL 구문을 걱정하지 않아도 된다. 
- 결과적으로 데이터가 동적으로 늘어나도 같은 SQL 구문을 그대로 사용해서 성능을 최적화 할 수 있다.
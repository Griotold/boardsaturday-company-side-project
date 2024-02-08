# 못한 부분

---

# 질문 사항
1. URL에 한글이 들어가도록 설계하는 거 

---

# 발견한 것

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

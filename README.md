# 못한 부분

- 수정용 상세보기
- 노출순서 바꾸기
---

# 질문 사항
1. ResponseEntity<?>
2. 테스트를 위한 메서드
3. service에 @Transactional(readOnly = true)을 붙이면 안되는 이유

---

# 발견한 것
1. 스프링 통합 테스트 시 @Transactional로 DB를 롤백 시키는데 id는 여전히 건너뛰어지는 이유
- https://www.inflearn.com/questions/1148883/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%86%B5%ED%95%A9-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%8B%9C-transactional%EB%A1%9C-db%EB%A5%BC-%EB%A1%A4%EB%B0%B1-%EC%8B%9C%ED%82%A4%EB%8A%94%EB%8D%B0-id%EB%8A%94-%EC%97%AC%EC%A0%84%ED%9E%88-%EA%B1%B4%EB%84%88%EB%9B%B0%EC%96%B4%EC%A7%80%EB%8A%94-%EC%9D%B4%EC%9C%A0
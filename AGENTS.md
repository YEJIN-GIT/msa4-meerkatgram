# Meerkatgram 문서 인덱스 (meerkatgram-doc)

이 문서는 [meerkatgram-doc](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc) 디렉터리에 포함된 백엔드 및 프론트엔드 개발·설계 문서를 정리한 가이드입니다. 각 링크를 클릭하여 상세 문서의 내용을 바로 확인하실 수 있습니다.

---

## ☕ 백엔드 관련 문서 목록 (1st-doc)

| 번호 | 문서명 | 파일 링크 (상대 경로) | 파일 링크 (절대 경로) | 주요 내용 |
| :--- | :--- | :--- | :--- | :--- |
| **01** | **01. 프로젝트 개요** | [01-project-overview.md](./meerkatgram-doc/1st-doc/01-project-overview.md) | [01-project-overview.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/01-project-overview.md) | 프로젝트 소개, 주요 기능 목록(인증, 유저, 게시글, 파일) |
| **02** | **02. ERD & 데이터베이스** | [02-erd-and-database.md](./meerkatgram-doc/1st-doc/02-erd-and-database.md) | [02-erd-and-database.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/02-erd-and-database.md) | ERD 다이어그램, 테이블 스키마 구성, 외래키 관계 및 소프트 삭제 설계 |
| **03** | **03. 백엔드 아키텍처** | [03-backend-architecture.md](./meerkatgram-doc/1st-doc/03-backend-architecture.md) | [03-backend-architecture.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/03-backend-architecture.md) | 레이어드 아키텍처(Controller, Service, Repository, Entity, DTO) 및 패키지 구조 |
| **04** | **04. API 명세서** | [04-api-specification.md](./meerkatgram-doc/1st-doc/04-api-specification.md) | [04-api-specification.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/04-api-specification.md) | 공통 요청/응답 규격, 상세 API 엔드포인트 명세 및 요청/응답 JSON 예시 |
| **05** | **05. JWT 인증 구현 가이드** | [05-auth-jwt-guide.md](./meerkatgram-doc/1st-doc/05-auth-jwt-guide.md) | [05-auth-jwt-guide.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/05-auth-jwt-guide.md) | JWT 인증 메커니즘, 로그인/로그아웃/재발급 시퀀스 다이어그램 및 시큐리티 필터 설정 |
| **06** | **06. 핵심 기능 구현 가이드** | [06-key-features-guide.md](./meerkatgram-doc/1st-doc/06-key-features-guide.md) | [06-key-features-guide.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/06-key-features-guide.md) | 공통 응답 DTO, 예외 처리 핸들러 및 로컬 파일 업로드/관리 상세 구현 |
| **07** | **07. 환경 설정 및 실행 가이드** | [07-setup-guide.md](./meerkatgram-doc/1st-doc/07-setup-guide.md) | [07-setup-guide.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/07-setup-guide.md) | 개발 도구 요구사항, 데이터베이스 생성 및 스키마 적용, 백엔드 서버 로컬 실행 방법 |
| **08** | **08. API Response 종류 정리** | [api-response-summary.md](./meerkatgram-doc/1st-doc/api-response-summary.md) | [api-response-summary.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/api-response-summary.md) | 백엔드 API별 HttpStatus & 에러코드 응답 구조 요약 정리 |

---

## 📄 프론트엔드 관련 문서 목록 (1st-doc)

| 번호 | 문서명 | 파일 링크 (상대 경로) | 파일 링크 (절대 경로) | 주요 내용 |
| :--- | :--- | :--- | :--- | :--- |
| **09** | **09. 프론트엔드 개요** | [08-frontend-overview.md](./meerkatgram-doc/1st-doc/08-frontend-overview.md) | [08-frontend-overview.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/08-frontend-overview.md) | Vue 3 프론트엔드 기술 스택, 디렉터리 구조, 전체 데이터 흐름 및 에러 처리 흐름 |
| **10** | **10. 프론트엔드 아키텍처** | [09-frontend-architecture.md](./meerkatgram-doc/1st-doc/09-frontend-architecture.md) | [09-frontend-architecture.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/09-frontend-architecture.md) | 3계층 레이어 구조(View, Store, API), Axios 인터셉터 설정, 라우팅 및 네비게이션 가드 설계 |
| **11** | **11. 프론트엔드 인증 흐름** | [10-frontend-auth-flow.md](./meerkatgram-doc/1st-doc/10-frontend-auth-flow.md) | [10-frontend-auth-flow.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/10-frontend-auth-flow.md) | Access/Refresh Token 이중 토큰 인증, 로그인/로그아웃/토큰 갱신 시퀀스 다이어그램 및 세부 동작 |
| **12** | **12. 프론트엔드 컴포넌트 가이드** | [11-frontend-components.md](./meerkatgram-doc/1st-doc/11-frontend-components.md) | [11-frontend-components.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/11-frontend-components.md) | `MyButton`, `MyInput` 등 공통 컴포넌트의 Props 명세 및 사용 예시 |
| **13** | **13. 프론트엔드 환경 설정 및 실행 가이드** | [12-frontend-setup.md](./meerkatgram-doc/1st-doc/12-frontend-setup.md) | [12-frontend-setup.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/1st-doc/12-frontend-setup.md) | 개발 서버(Vite) 설치, `.env` 환경 변수 구성 및 로컬 실행 방법 |

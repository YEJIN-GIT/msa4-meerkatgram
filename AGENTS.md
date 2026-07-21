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
| **09** | **09. API 응답 및 에러 코드 명세서** | [api_responses.md](./meerkatgram-doc/api_responses.md) | [api_responses.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/api_responses.md) | 백엔드 API별 성공 응답 및 에러 코드 대/중/소분류 통합 상세 명세 |

---

## 🔍 프로젝트 분석 문서 목록 (analysis)

| 번호 | 문서명 | 파일 링크 (상대 경로) | 파일 링크 (절대 경로) | 주요 내용 |
| :--- | :--- | :--- | :--- | :--- |
| **01** | **01. 비전공자 서비스 흐름 설명서** | [01-non-technical-guide.md](./meerkatgram-doc/analysis/01-non-technical-guide.md) | [01-non-technical-guide.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/analysis/01-non-technical-guide.md) | 식당 비유를 활용한 비전공자용 시스템 구조 및 회원가입/로그인/소프트삭제 흐름 설명 |
| **02** | **02. 프로젝트 아키텍처 및 레이어 가이드** | [02-architecture-and-layers.md](./meerkatgram-doc/analysis/02-architecture-and-layers.md) | [02-architecture-and-layers.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/analysis/02-architecture-and-layers.md) | 레이어드 아키텍처, 도메인-글로벌 패키지 단방향 의존성 규칙, JWT 보안 및 QueryDSL N+1 최적화 |
| **03** | **03. 코드 컨벤션 및 구현 규칙** | [03-code-convention.md](./meerkatgram-doc/analysis/03-code-convention.md) | [03-code-convention.md](file:///E:/jin/workspace/msa4-meerkatgram/meerkatgram-doc/analysis/03-code-convention.md) | 명명 규칙, DTO record 설계, Lombok DI, 외래키 제약조건 제거 및 글로벌 예외 처리 규칙 |

# Baseball Game App ⚾️

온라인 대결이 가능한 간단한 야구 게임 앱 제작 프로젝트

<br>

## 기능 소개

### 게임 선택
<img src="https://user-images.githubusercontent.com/72188416/118230130-1a24a480-b4c8-11eb-9ae2-3b841958bdf0.gif" width="350">

- 자신의 닉네임을 입력하여 게임에 참여할 수 있다. (OAuth 로그인 미구현)
- 서버에서 가지고 있는 게임 데이터를 받아 온다.
- 게임 중 플레이 가능한 팀을 선택하여 플레이할 수 있다.


### 게임 플레이
<img src="https://user-images.githubusercontent.com/72188416/118229864-b4d0b380-b4c7-11eb-85cf-cd9eb96001c5.gif" width="350">

- 수비 턴에 `pitch` 버튼을 터치하여 게임을 진행시킬 수 있다.
- `pitch` 시 서버에 게임 진행을 요청하고, 변경된 데이터를 받아온다.

`게임 확률표`

|스트라이크|볼|안타|
|-------|--|---|
|50.0%|40.0%|10.0%|

- 그래픽 및 애니메이션을 통해 화면을 실시간으로 업데이트한다. 
- 점수가 나거나 이닝이 바뀔 시 알림창을 띄운다.
- 공격 턴은 아직 구현하지 못한 상태이다. 


<br>


## 팀 소개

### 팀원 소개


| 파트 | 닉네임                               | 소개                   |
| ---- | ------------------------------------ | ---------------------- |
| BE   | [Shion](https://github.com/ehdrhelr) | 칼퇴 요정 🧚            |
| iOS  | [Lia](https://github.com/Lia316)     | 뷰 깎는 어린이 👶🏻      |
| iOS  | [Lollo](https://github.com/eeeesong) | 리드미 마스터 꿈나무 🌳 |


### 팀의 방향성

1. 자주 빠르게 소통하자
2. 6시 퇴근을 추구하자
3. issues를 적극 활용하자



### 브랜치 전략

파트 별 `dev` 브랜치에서 분기

```javascript
team-06 (default)
    |
    |--> dev/BE
    |      |
    |      |--> feature...
    |
    |--> dev/iOS
    |      |
    |      |--> feature...
```

- BE 진행 상황 - [dev/BE](https://github.com/ehdrhelr/baseball/tree/dev/BE)
- iOS 진행 상황 - [dev/iOS](https://github.com/ehdrhelr/baseball/tree/dev/iOS)


### 회의

1. 전체 회의: 2시 & 5시 30분
2. iOS 회의: 안건에 따라 유동적으로 진행

> 회의 방향성: 짧고 간결하게! 필요한 말만!


전체 회의록 | iOS 회의록
-- | --
[5월 3일](https://github.com/ehdrhelr/baseball/wiki/5월-3일-전체-회의록) | [5월 3일](https://github.com/ehdrhelr/baseball/wiki/5월-3일-iOS-회의록)
[5월 4일](https://github.com/ehdrhelr/baseball/wiki/5월-4일-전체-회의록) | [5월 4일](https://github.com/ehdrhelr/baseball/wiki/5월-4일-iOS-회의)
[5월 6일](https://github.com/ehdrhelr/baseball/wiki/5월-6일-전체-회의)| [5월 6일](https://github.com/ehdrhelr/baseball/wiki/5월-6일-iOS-회의)
[5월 7일](https://github.com/ehdrhelr/baseball/wiki/5월-7일-전체-회의록) | [5월 7일](https://github.com/ehdrhelr/baseball/wiki/5월-7일-iOS-회의록)
[5월 10일](https://github.com/ehdrhelr/baseball/wiki/5월-10일-전체-회의) | [5월 10일](https://github.com/ehdrhelr/baseball/wiki/5월-10일-iOS-회의록)
[5월 11일](https://github.com/ehdrhelr/baseball/wiki/5월-11일-전체-회의록)| [5월 11일](https://github.com/ehdrhelr/baseball/wiki/5월-11일-iOS-회의록) 
[5월 12일](https://github.com/ehdrhelr/baseball/wiki/5월-12일-전체-회의록)| [5월 12일](https://github.com/ehdrhelr/baseball/wiki/5월-12일-iOS-회의록)
[5월 14일](https://github.com/ehdrhelr/baseball/wiki/5월-14일-전체-회의록)||


### [팀 Wiki](https://github.com/ehdrhelr/baseball/wiki)

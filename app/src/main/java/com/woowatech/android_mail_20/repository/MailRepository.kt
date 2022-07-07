package com.woowatech.android_mail_20.repository

import com.woowatech.android_mail_20.response.MailResponse

class MailRepository {
    fun getPrimary(): List<MailResponse> {
        return listOf(
            MailResponse(
                "https://ko.m.wikipedia.org/wiki/파일:Apple_logo_black.svg",
                "Apple",
                "Your Apple ID was used to sign in to iCloud on a MacBook Pro 15",
                "Dear 윤재 양, Your Apple ID (2004yyj@naver.com) was used to sign in to iCloud on a MacBook Pro 15."
            ),
            MailResponse(
                "https://www.aladin.co.kr/m/mcscenter.aspx?pType=qsadd",
                "Naver",
                "새로운 환경에서 로그인 되었습니다.",
                "회원님의 아이디 ****이(가) 새로운 환경에서 로그인 되었습니다. 아래의 로그인이 회원님의 활동이 맞는지 확인해주세요."
            ),
            MailResponse(
                "https://icon-icons.com/ko/아이콘/github-로고/143772",
                "Github",
                "[GitHub] A third-party OAuth application has been added to your account",
                "A third-party OAuth application (asdf) with read:user and user:email scopes was recently authorized to access your account."
            ),
        )
    }
    fun getSocial(): List<MailResponse> {
        return listOf(
            MailResponse(
                "https://ko.wikipedia.org/wiki/파일:트위터_로고_%282012%29.svg",
                "Twitter",
                "Highland Park suspect charged with seven counts of first-degree mur... ",
                "What’s happening"
            )
        )
    }
    fun getPromotion(): List<MailResponse> {
        return listOf(
            MailResponse(
                "https://m.blog.naver.com/adobe_kr/221656761697",
                "우아한형제들",
                "[우아한형제들] 배달 들어갑니다",
                "다 배달해드립니다. 짜장면 짬뽕 탕수육 모조리 기다려"
            ),
            MailResponse(
                "https://twitter.com/googlekorea",
                "Google",
                "보안 알림",
                "Android에서 새로 로그인함 asdf@gmail.com"
            ),
            MailResponse(
                "https://play.google.com/store/apps/details?id=notion.id&hl=ko&gl=TR",
                "Notion",
                "Teams that use Notion save time. Does yours? ",
                "Hi 양윤재, Is your team as efficient and innovative as you'd like?"
            ),
            MailResponse(
                "https://www.rocketpunch.com/tag/dropbox-51hnbv",
                "RocketPunch",
                "양윤재 님이 관심 있을 만한 이번 주 추천 채용 정보를 확인해 보세요. ",
                "업무 분야 / 희망 포지션 / 활동 분야에 맞는 채용정보 10개를 알려드립니다."
            ),
            MailResponse(
                "https://slack.com/intl/ko-kr/",
                "Slack",
                " 여러분을 Why Slack 무료 웨비나에 초대합니다.",
                "Why Slack with Baemin?"
            ),
        )
    }
}
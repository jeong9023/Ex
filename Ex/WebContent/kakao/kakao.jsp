<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>땅땅땅 경매장에게 문의하기</title>
<link href="../css/kakao.css" rel="stylesheet">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<div id="create-channel-chat-button" class="kakao"></div>
<script type="text/javascript">
  // input your appkey
  Kakao.init('927fa51644a5e29c34e42f5bfa1792aa')
  Kakao.Channel.createChatButton({
    container: '#create-channel-chat-button',
    channelPublicId: '_PPxoNxb',
  })
</script>


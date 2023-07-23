import http from '../utils/http'
let request = "http://81.68.137.192:8888"

//用户
export function login(params){
  return http.post(`${request}/userinfo/login`,params)
}
export function getAttention(params){
  return http.post(`${request}/userinfo/getAttention`,params)
}
export function deleteAttention(params){
  return http.post(`${request}/userinfo/deleteAttention`,params)
}
export function getAttentionTo(params){
  return http.post(`${request}/userinfo/getAttentionTo`,params)
}
export function getUserInfo(params){
  return http.post(`${request}/userinfo/getUserInfo`,params)
}
export function updateUserInfo(params){
  return http.post(`${request}/userinfo/updateUserInfo`,params)
}
export function selectHistory(params){
  return http.post(`${request}/userinfo/selectHistory`,params)
}
export function getFavoriteVideo(params){
  return http.post(`${request}/userinfo/getFavoriteVideo`,params)
}
export function attention(params){
  return http.post(`${request}/userinfo/attention`,params)
}
export function selectAttend(params){
  return http.post(`${request}/userinfo/selectAttend`,params)
}
export function getUserLikeNum(params){
  return http.post(`${request}/userinfo/getUserLikeNum`,params)
}
export function getUserPlayNum(params){
  return http.post(`${request}/userinfo/getUserPlayNum`,params)
}
export function getUserWork(params){
  return http.post(`${request}/userinfo/getUserWork`,params)
}
export function sendMsg(params){
  return http.post(`${request}/userinfo/sendMsg`,params)
}
export function addUser(params){
  return http.post(`${request}/userinfo/addUser`,params)
}
export function loginByEmail(params){
  return http.post(`${request}/userinfo/loginByEmail`,params)
}
export function getFollowedVideo(params){
  return http.post(`${request}/userinfo/getFollowedVideo`,params)
}


//视频
export function getVideoType(params){
  return http.post(`${request}/video/getVideoType`,params)
}
export function getVideoByType(params){
  return http.post(`${request}/video/getVideoByType`,params)
}
export function rank(params){
  return http.post(`${request}/video/rank`,params)
}
export function getVideoById(params){
  return http.post(`${request}/video/getVideoById`,params)
}
export function getVideoCommentById(params){
  return http.post(`${request}/video/getVideoCommentById`,params)
}
export function recommend(params){
  return http.post(`${request}/video/recommend`,params)
}
export function setLikeVideo(params){
  return http.post(`${request}/video/setLikeVideo`,params)
}
export function comment(params){
  return http.post(`${request}/video/comment`,params)
}
export function setFavoriteVideo(params){
  return http.post(`${request}/video/setFavoriteVideo`,params)
}
export function selectLike(params){
  return http.post(`${request}/video/selectLike`,params)
}
export function selectFavorite(params){
  return http.post(`${request}/video/selectFavorite`,params)
}
export function playVideo(params){
  return http.post(`${request}/video/play`,params)
}
export function addVideo(params){
  return http.post(`${request}/video/addVideo`,params)
}
export function search(params){
  return http.post(`${request}/video/search`,params)
}


//消息
export function getAllMessage(params){
  return http.post(`${request}/message/getAllMessage`,params)
}
export function addMessage(params){
  return http.post(`${request}/message/addMessage`,params)
}
export function selectToUser(params){
  return http.post(`${request}/message/selectToUser`,params)
}

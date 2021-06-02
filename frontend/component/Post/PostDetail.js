import React, { useEffect } from "react"
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    useColorScheme,
    ScrollView,
    TextInput,
    StyleSheet,
    Image,
    Button,
    Alert
} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";

const CommentForm = ({ userIcon, username, content, date}) => {
    return (
        <View 
            style={{
                display: "flex",
                alignSelf: "center",
                borderBottomColor: '#BBBBBB',
                borderBottomWidth: 1,
                width: "100%",
                marginTop: 10,
        }}>
            <View 
                style={{
                    display: "flex",
                    flexDirection: "row",
                    marginLeft: "2%"
            }}>
                <Image style={{
                        width: 30,
                        height: 30,
                    }} 
                    source={require('../icon/default_profile.png')} />
                <Text
                    style={{
                        fontWeight: "bold",
                        fontSize: 15
                }}>
                    {username}
                </Text>
            </View>
            <Text style={{
                color: "#555555",
                marginLeft: "2%",
            }}>
                {content}
            </Text>
            <Text style={{
                color: "#AAAAAA",
                marginLeft: "2%",
                fontSize: 10,
                marginTop: 5,
                marginBottom: 10
            }}>
                {date}
            </Text>
        </View>
    )
}

export default function PostDetail({route, navigation}) {
    const isDarkMode = useColorScheme() === 'dark';
    const [title, setTitle] = React.useState("default title");
    const [content, setContent] = React.useState("default content");
    const [imageData, setImageData] = React.useState("");
    const [profile, setProfile] = React.useState("");
    const [userName, setUserName] = React.useState("");
    const [postDate, setPostDate] = React.useState("");
    const [commnetData, setCommentData] = React.useState([]);
    const [commentContent, setCommentContent] = React.useState("");
    const { token, postId, boardType } = route.params;
    const [flag, setFlag] = React.useState(0);
    
    function getPost() {
        fetch(`http://3.139.204.200:8080/post/find/${postId.postId}`, {
            method: 'GET',
            credentials: true,
            headers: {
                "Accept": "application/json",
            }
        }).then(res => res.json()

        ).then(response => {
            setTitle(response.title);
            setContent(response.contents);
            setImageData(response.images[0]);
            setProfile(response.user.profile);
            setUserName(response.user.nickname);
            setPostDate(response.createdDate);
            setCommentData(response.commentDtoList);
        }).catch((error) => {
            //console.log(response);
            console.log(error);
        });

    }

    useEffect(getPost, [flag]);
   
    async function likeButtonHandler(){
        var data = {
            "title" : title,
            "contents" : content,
            "images" : [imageData],
            "category" : boardType
        }
        console.log(JSON.stringify(data));
        await fetch('http://3.139.204.200:8080/post/save/1', {
            method: 'POST',
            credentials: true,
            headers: {
                "Accept": "application/json",
                'Content-type': 'application/json',
                'X-AUTH-TOKEN': token
            },
            body: JSON.stringify(data)
        }).then(res => JSON.stringify(res))
        .then(response => {
            navigation.goBack();
        })
        .catch((error) => {
            console.log(error);
        });
    }

    async function commentPostButtonHandler(){
        var data = {
            "comment": commentContent
        }
        console.log(JSON.stringify(data));
        await fetch(`http://3.139.204.200:8080/comment/save/${postId.postId}`, {
            method: 'POST',
            credentials: true,
            headers: {
                "Accept": "application/json",
                'Content-type': 'application/json',
                'X-AUTH-TOKEN': token
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            setFlag(flag + 1);

        })
        .catch((error) => {
            console.log(error);
        });
    }
    
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <ScrollView contentInsetAdjustmentBehavior="automatic"
                    style={{
                        marginLeft: "5%",
                        marginRight: "5%"
                    }}>
                <View style={{
                        display: "flex",
                        flexDirection: "row",
                        marginTop: 5,
                        marginBottom: 20
                    }}>
                    <Image style={{
                            alignSelf: "center",
                            width: 20,
                            height: 20,
                            marginRight: 15
                        }}
                        source={require("../icon/arrow.png")}/>
                    <View style={{
                        width: "80%"
                    }}>
                        <Text style={{
                            fontSize: 30,
                            fontWeight: "bold",
                            color: "#000000",
                        }}>
                            {boardType == "free" ? "자유게시판" : "토론게시판"}
                        </Text>
                    </View>
                </View>
                <View style={{
                        display: "flex",
                        flexDirection: "row",
                        marginTop: 5,
                        marginBottom: 20
                    }}>
                    <Image
                        source={require('../icon/default_profile.png')}
                        style={{
                            width: 40,
                            height: 40,
                            marginRight: 10
                        }}
                    />
                    <View style={{
                        width: "75%",
                    }}>
                        <Text
                            style={{
                                fontSize: 15,
                                fontWeight: "bold",    
                            }}>
                            {userName}
                        </Text>
                        <Text
                            style={{
                                color: "#555555"    
                            }}>
                            {postDate}
                        </Text>
                    </View>
                    <TouchableOpacity onPress={likeButtonHandler} style={styles.button}>
                        <Text style={{
                            fontSize: 12,
                            fontWeight: "bold",
                            color: "#000000"
                        }}>좋아요</Text>
                    </TouchableOpacity>
                </View>
                <Text
                    style={styles.title}
                >
                    {title}
                </Text>
                <Text
                    style={styles.content}
                >
                    {content}
                </Text>
                <Image
                    source={imageData ? { uri: `data:image/jpa;base64,${imageData}` } : null}
                    style={imageData ? {width: 300, height: 200, marginLeft: "5%"} : {width: 0, height: 0} }
                /> 
                <View
                    style={{
                        display: "flex",
                        flexDirection: "row",
                        marginTop: 10,
                        borderBottomColor: '#BBBBBB',
                        borderBottomWidth: 1,
                        justifyContent: "flex-end",
                }}>
                    <Image style={{
                            marginTop: 0,
                            marginBottom: 15,
                            width: 16,
                            height: 17,
                        }}
                        source={require("../icon/thumbsUp.png")}/>
                    <Text
                        style={{
                            marginLeft: 5,
                            marginRight: 5
                     }}>
                        0
                    </Text>
                    <Image style={{
                            marginLeft: 7,
                            marginTop: 3,
                            marginBottom: 12,
                            width: 18,
                            height: 15,
                        }}
                        source={require("../icon/comment.png")}/>
                    <Text
                        style={{
                            marginLeft: 5,
                    }}>
                        {commnetData.length}
                    </Text>
                </View>
                <View
                    style={{
                        display: "flex",
                        marginTop: 10,
                        borderBottomColor: '#BBBBBB',
                        borderBottomWidth: 1,
                }}>
                    <Text
                        style={{
                            marginLeft: "2%"
                    }}>
                        댓글 쓰기
                    </Text>
                    <TextInput
                        style={styles.comment}
                        onChangeText={text => setCommentContent(text)}
                        placeholder="comment"
                        defaultvalue={commentContent}
                    /> 
                    <View 
                        style={{
                            display: "flex",
                            flexDirection: "row",
                            justifyContent: "flex-end",
                    }}>
                        <TouchableOpacity onPress={commentPostButtonHandler} style={styles.button}>
                            <Text style={{
                                fontSize: 12,
                                fontWeight: "bold",
                                color: "#000000"
                            }}>등록</Text>
                        </TouchableOpacity>
                    </View>
                </View>
                {commnetData.map(item => (<CommentForm userIcon={item.user.profile} username={item.user.nickname} content={item.comments} date={item.createdDate} />))}
            </ScrollView>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    title: {
        height: 50,
        width: "90%",
        marginTop: 10,
        fontSize: 25,
    },
    content: {
        width: "90%",
        marginTop: 10,
        fontSize: 20,
        color: "#555555",
        flex: 1,
        justifyContent: 'flex-start',
    },
    comment: {
        height: 50,
        width: "90%",
        fontSize: 15,
        marginLeft: "2%",
    },
    button: {
        width: 70,
        height: 30,
        borderRadius: 15,
        backgroundColor: "#EEEEEE",
        borderColor: "#000000",
        marginTop: 5,
        marginBottom: 10,

        display: "flex",
        alignItems: "center",
        justifyContent: "center" 
    },
    buttonText: {
      textAlign: 'center',
      fontSize: 15,
      color: '#fff'
    }
  });
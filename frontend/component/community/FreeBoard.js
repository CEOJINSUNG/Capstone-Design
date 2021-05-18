import React, { useEffect } from "react"
import { 
    View,
    Text,
    Image,
    ScrollView, 
    SafeAreaView,
    TextInput} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";

//TODO : imageDir to image from DB
const PostForm = ({ navigation, token, title, content, boardType, encodedData}) => {
    return (
        <TouchableOpacity 
            onPress={() => navigation.navigate("PostDetail", {token: token, postId: 1, boardType: boardType})}
            style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                justifyContent: "space-between",
                alignSelf: "center",
                width: "90%",
                height: 60,
                marginTop: 10,
        }}>
            <Image style={{
                    width: "20%",
                    height: "100%",
                }} 
                source={{ uri: `data:image/jpa;base64,${encodedData}` }} />
            <View style={{
                    width: "65%"
                }}>
                <Text style={{
                    fontSize: 12,
                    fontWeight: "bold",
                    color: "#000000",
                }}>{title}</Text>
                <Text style={{
                    fontSize: 10,
                    color: "#000000",
                    marginTop: 4,
                }}>{content}</Text>
            </View>
            <View style={{
                    width: "10%",
                    height: 70,
                }}>
                <View style={{
                    flex: 1,
                    justifyContent: 'flex-end',
                }}>
                    <View style={{
                        display: "flex",
                        flexDirection: "row",
                        marginBottom: 3,
                    }}>
                    </View>
                </View>
            </View>
        </TouchableOpacity>
    )
}

export default function FreeBoard({token, boardType, navigation}) {   
    const [search, setSearch] = React.useState("");
    const [lists, setLists] = React.useState([]);

    async function getPosts() {
        fetch('http://3.139.204.200:8080/post/list/0/5', {
            method: 'GET',
            credentials: true,
            headers: {
                "Accept": "application/json",
            }
        }).then(res =>
            res.json()
        ).then(response => {
            setLists(response);
            console.log(response);
        }).catch((error) => {
            console.log(error);
        });
    }
    console.log(lists);

    useEffect(getPosts, []);

    return(
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <ScrollView contentInsetAdjustmentBehavior="automatic">
                <View style={{
                        display: "flex",
                        flexDirection: "row",
                        justifyContent: "space-between",
                        marginTop: 20,
                        marginBottom: 20,
                        marginLeft: "5%"
                    }}>
                    <View style={{
                        width: "25%"
                    }}>
                        <Text style={{
                            fontSize: 30,
                            fontWeight: "bold",
                            color: "#000000",
                        }}>
                            {boardType == "free" ? "자유게시판" : "토론게시판"}
                        </Text>
                    </View>
                    <TouchableOpacity onPress={() => navigation.navigate("Posting", { boardType: boardType, "token": token })} style={{
                        width: 50,
                        height: 30,
                        backgroundColor: "#650AB2",
                        borderRadius: 8,
                        alignSelf: "center",
                        marginTop: 5,

                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center"
                    }}>
                        <Text style={{
                            fontSize: 12,
                            fontWeight: "bold",
                            color: "#ffffff"
                        }}>글쓰기</Text>
                    </TouchableOpacity>
                    <View style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                        width: "40%"
                    }}>
                        <TextInput
                            style={{
                                height: 40,
                                width: "70%",
                                borderWidth: 1,
                                borderRadius: 5,
                                fontSize: 10
                            }}
                            onChangeText={text => setSearch(text)}
                            defaultvalue={search}
                            placeholder="제목, 내용 검색"
                        
                        />
                        <Image style={{
                                marginLeft: 10,
                                marginTop: 0,
                                width: 20,
                                height: 20,
                            }}
                            source={require("../icon/search.png")}/>
                    </View>
                </View>
                {lists.map(item => (<PostForm navigation={navigation} token={token} title={item.title} content={item.title} encodedData={item.image} />))}

            </ScrollView>
        </SafeAreaView>
       
    );
}      
                
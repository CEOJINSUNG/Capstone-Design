import React from "react";
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from "react-native-vector-icons/Ionicons";
import FC from './FC';
import Personal from './Personal';
import Community from './Community';

const Tab = createBottomTabNavigator();

export default function Main({ route, navigation }) {
    const {token} = route.params;
    console.log(JSON.stringify(token));
    return (
        <Tab.Navigator
            screenOptions={({ route }) => ({
                tabBarLabel: ({focused}) => {
                    return null
                },
                tabBarIcon: ({ focused, color, size }) => {
                    let iconName;

                    if (route.name === 'Community') {
                        iconName = focused
                            ? 'chatbox-ellipses'
                            : 'chatbox-ellipses-outline';
                    } else if (route.name === 'FC') {
                        iconName = focused ? 'football' : 'football-outline';
                    } else if (route.name === "Personal") {
                        iconName = focused ? "person-circle" : "person-circle-outline"
                    }

                    return <Ionicons name={iconName} size={24} color={color} />;
                },
            })
        }
            tabBarOptions={{
                activeTintColor: '#650ab2',
                inactiveTintColor: '#000000',
            }}
        >
            <Tab.Screen name="Community" component={Community}/>
            <Tab.Screen name="FC" component={FC} />
            <Tab.Screen name="Personal" component={Personal} />
        </Tab.Navigator>
    );
}
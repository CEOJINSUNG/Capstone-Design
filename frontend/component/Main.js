import React from "react"

import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from "react-native-vector-icons/Ionicons"

import FC from './FC';
import Personal from './Personal';
import Splash from './Splash';
import Community from './Community';

const Tab = createBottomTabNavigator();

export default function Main({ navigation }) {
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
            })}
            tabBarOptions={{
                activeTintColor: '#650ab2',
                inactiveTintColor: '#000000',
            }}
        >
            <Tab.Screen name="Community" component={Community} />
            <Tab.Screen name="FC" component={FC} />
            <Tab.Screen name="Personal" component={Personal} />
        </Tab.Navigator>
    );
}
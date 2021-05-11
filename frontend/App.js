import { NavigationContainer, StackActions } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import "./globals"

import React from 'react';
import Home from './component/Home';
import Info from './component/Info';
import Main from './component/Main';
import Purchase from './component/Purchase';
import Shop from './component/Shop';
import SignUp from './component/SignUp';
import Splash from './component/Splash';

function App() {
  const Stack = createStackNavigator();
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">
        <Stack.Screen name="Splash" component={Splash} options={{
          headerShown: false
        }} />
        <Stack.Screen name="Home" component={Home}
          options={{headerShown: false}} />
        <Stack.Screen name="SignUp" component={SignUp}
          options={{headerShown: false}} />
        <Stack.Screen name="Main" component={Main}
          options={{headerShown: false}} />
        <Stack.Screen name="Shop" component={Shop}
          options={{headerShown: false}} />
        <Stack.Screen name="Purchase" component={Purchase} 
          options={{headerShown: false}} />
        <Stack.Screen name="Info" component={Info}
          options={{headerShown: false}} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
 
export default App;
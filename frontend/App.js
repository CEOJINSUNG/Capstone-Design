import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import React from 'react';
import Home from './component/Home';
import Main from './component/Main';
import SignUp from './component/SignUp';

function App() {
  const Stack = createStackNavigator();
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={Home}
          options={{headerShown: false}} />
        <Stack.Screen name="SignUp" component={SignUp}
          options={{headerShown: false}} />
        <Stack.Screen name="Main" component={Main}
          options={{headerShown: false}} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
 
export default App;

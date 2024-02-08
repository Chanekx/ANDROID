import React, { useState } from 'react';
import { StyleSheet, Text, View, TextInput, Button, FlatList } from 'react-native';

export default function App() {
  const [name, setName] = useState(''); 
  const [course, setCourse] = useState(''); 
  const [level, setLevel] = useState(''); 

  const [students, setStudents] = useState([]); 

  
  const addNameToList = () => {
    if (name.trim() !== '' && course.trim() !== '' && level.trim() !== '') {
      setStudents(prevStudents => [...prevStudents, { name,course,level }]);
      setName('');
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.textInput}
        value={name}
        onChangeText={text => setName(text)}
        placeholder="Enter name"
      />
      <TextInput
        style={styles.textInput}
        value={course}
        onChangeText={text => setCourse(text)}
        placeholder="Enter Course"
      />
      <TextInput
        style={styles.textInput}
        value={level}
        onChangeText={text => setLevel(text)}
        placeholder="Enter level"
      />
      <Button title="Add" onPress={addNameToList} color="#8E8FFA" />
      <FlatList
  data={students}
  keyExtractor={(item, index) => index.toString()}
  renderItem={({ item }) => (
    <View style={styles.studentInfo}>
      <Text style={styles.studentName}>Name: {item.name}</Text>
      <Text style={styles.studentInfoText}> Course: {item.course}</Text>
      <Text style={styles.studentInfoText}> Level: {item.level}</Text>
    </View>
  )}
/>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#8E8FFA',
    alignItems: 'center',
    padding: 20,
  },
  textInput: {
    backgroundColor: '#7752FE',
    width: '30%',
    padding: 10,
    marginBottom: 10,
    borderRadius: 5,
  },
  studentName: {
    fontSize: 18,
    marginVertical: 5,
  },
  studentInfo: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  studentInfoText: {
    fontSize: 16,
    marginVertical: 2,
  },
  
});

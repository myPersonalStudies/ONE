const friends = ['Isaac', 'John', 'Jane', 'Doe'];

const addFriendButton = document.querySelector('.button-add');
const drawFriendButton = document.querySelector('.button-draw');

addFriendButton.addEventListener('click', () => addFriendToList());
drawFriendButton.addEventListener('click', () => drawFriends());

const addFriendToList = () => {

    const friendName = document.querySelector('.input-name').value;

    if (validateFriendName(friendName)[0]) {

        friends.push(friendName);
        console.log(friends);

        alert('Friend added successfully');
    }
    
    else {

        alert(validateFriendName(friendName)[1]);         
    }
}

const validateFriendName = (name) => {

    if (name === '')  return [false, 'Name cannot be empty'];
    return [true];
}

const generateRandomNumber = () => Math.floor(Math.random() * friends.length);

const drawFriends = () => {

    drawIndex = generateRandomNumber();
    console.log(friends[drawIndex]);
}



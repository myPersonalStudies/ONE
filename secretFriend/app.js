const friends = ['Isaac', 'John', 'Jane', 'Doe'];

const addFriendButton = document.querySelector('.button-add');
const drawFriendButton = document.querySelector('.button-draw');

addFriendButton.addEventListener('click', () => addFriendToList());
drawFriendButton.addEventListener('click', () => drawFriends());

const addFriendToList = () => {

    const friendName = document.querySelector('.input-name').value;

    const errorParagraph = document.querySelector('.error-message');

    if (validateFriendName(friendName)[0]) {

        errorParagraph.style.display = 'block';
        errorParagraph.classList.add('success');
        errorParagraph.textContent = validateFriendName(friendName)[1];

        friends.push(friendName);
        console.log(friends);

        document.querySelector('.input-name').value = '';
    }
    
    else {
        
        errorParagraph.textContent = validateFriendName(friendName)[1];
        errorParagraph.classList.remove('success');
        errorParagraph.style.display = 'block';
    }
}

const validateFriendName = (name) => {

    if (name === '')  return [false, 'Name cannot be empty!'];
    return [true, `Your friend ${name} has been successfully added!`];
}

const generateRandomNumber = () => Math.floor(Math.random() * friends.length);

const drawFriends = () => {

    drawIndex = generateRandomNumber();
    console.log(friends[drawIndex]);

    modal(friends[drawIndex]);
}

modal = (name) => {

    const modal = document.querySelector('modal');
    const modalClose = document.querySelector('.button-close');

    const modalContent = document.querySelector('span');

    modal.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
    modal.style.backdropFilter = 'blur(5px)';
    modal.style.display = 'flex';

    modalContent.textContent = name + '!';

    modalClose.addEventListener('click', () => modal.style.display = 'none');
}


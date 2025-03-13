const friends = [];

const addFriendButton = document.querySelector('.button-add');
const drawFriendButton = document.querySelector('.button-draw');

drawFriendButton.disabled = true;

const handleDrawFriendMouseOver = () => alert('Você precisa adicionar pelo menos dois amigos para sortear!');
drawFriendButton.addEventListener('mouseover', handleDrawFriendMouseOver);

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

        document.querySelector('.input-name').value = '';

        if(friends.length >= 2) {

            drawFriendButton.disabled = false;
            drawFriendButton.removeEventListener('mouseover', handleDrawFriendMouseOver);
        }
    }
    
    else {
        
        errorParagraph.textContent = validateFriendName(friendName)[1];
        errorParagraph.classList.remove('success');
        errorParagraph.style.display = 'block';
    }
}

const validateFriendName = (name) => {

    if (name === '')  return [false, 'Acho que você se esqueceu do nome do amigo!'];
    return [true, `${name} foi adicionado(a) com sucesso!`];
}

const generateRandomNumber = () => Math.floor(Math.random() * friends.length);

const drawFriends = () => {

    drawIndex = generateRandomNumber();

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

document.querySelector('.input-name').addEventListener('keydown', (event) => {
    if (event.key === 'Enter') addFriendToList(); 
})
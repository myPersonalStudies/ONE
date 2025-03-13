# SecretFriend

Este projeto consiste em uma aplicação de Amigo Secreto feita com HTML, CSS e JavaScript. O objetivo da aplicação é permitir que os usuários insiram os nomes de seus amigos e sorteiem quem será o amigo secreto de cada um. Quando o sorteio é realizado, um modal é exibido com o nome do amigo sorteado.

## Tecnologias

## Estrutura do projeto
├── assets/
│   ├── amigo-secreto.png
│   ├── play_circle_outline.png
│
├── app.js
├── index.html
├── README.md
├── style.css

## Como rodar

1. Clone o repositório

```bash
    git clone https://github.com/DevSaLLein/ONE.
```

2. 

```bash
    cd SecretFriend
```

3. Abrir a página: Abra o arquivo `index.html` em seu navegador.

4. Adicionar Amigos:

- Digite o nome de um amigo no campo de entrada.
- Clique no botão "Adicionar" para adicionar o nome à lista.
- A lista de amigos será atualizada abaixo do campo de entrada.

5. Sortear o Amigo Secreto:

- Após adicionar pelo menos um amigo, clique no botão "Sortear amigo".
- O nome do amigo sorteado será exibido em um modal.
- Clique no botão "Fechar" no modal para fechá-lo.

## Funcionalidades

1. Adição de Amigos

A função `addFriendToList()` valida e adiciona amigos à lista. A validação garante que o nome não esteja vazio e exibe uma mensagem de erro ou sucesso conforme o caso.

```javascript
const addFriendToList = () => {

    const friendName = document.querySelector('.input-name') value;

    const errorParagraph = document.querySelector('.error-message');
    
    if (validateFriendName(friendName)[0]) {

        friends.push(friendName);
        errorParagraph.style.display = 'block';
        errorParagraph.classList.add('success');
        errorParagraph.textContent = validateFriendName(friendName)[1];

    } 
    
    else {

        errorParagraph.textContent = validateFriendName(friendName)[1];
        errorParagraph.classList.remove('success');
        errorParagraph.style.display = 'block';
    }
};

```

2. Sorteio de Amigos Secretos

A função `drawFriends()` sorteia aleatoriamente um amigo da lista e chama o modal para exibir o nome sorteado.

```javascript
const drawFriends = () => {

    drawIndex = generateRandomNumber();
    modal(friends[drawIndex]);
};
```

3. Exibição do Modal

O modal exibe o nome do amigo sorteado e escurece o fundo da página para destacar a informação. O modal pode ser fechado clicando no botão "Fechar".

```javascript
modal = (name) => {

    const modal = document.querySelector('modal');
    const modalClose = document.querySelector('.button-close');
    const modalContent = document.querySelector('span');
    
    modal.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
    modal.style.display = 'flex';
    modalContent.textContent = name + '!';
    
    modalClose.addEventListener('click', () => modal.style.display = 'none');
};

```

## Licença

Este projeto está licenciado sob a Licença Unlicense license. Para mais detalhes, consulte o arquivo LICENSE.

## Contribuições

Sinta-se à vontade para contribuir com melhorias ou sugestões. Para isso, basta criar um pull request ou abrir uma issue com a sua proposta.
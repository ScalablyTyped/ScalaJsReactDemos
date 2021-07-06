import { configure } from '@storybook/react';

function loadStories() {
    require('../target/scala-3.0.0/storybook-react-fastopt.js');
}

configure(loadStories, module);

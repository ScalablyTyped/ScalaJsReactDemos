import { configure } from '@storybook/react';

function loadStories() {
    require('../target/scala-3.0.1/storybook-react-fastopt.js');
}

configure(loadStories, module);

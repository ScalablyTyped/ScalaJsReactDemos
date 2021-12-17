import { configure } from '@storybook/react';

function loadStories() {
    require('../target/scala-3.1.0/storybook-react-opt.js');
}

configure(loadStories, module);

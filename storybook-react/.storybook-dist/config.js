import { configure } from '@storybook/react';

function loadStories() {
    require('../target/scala-3.0.1/storybook-react-opt.js');
}

configure(loadStories, module);

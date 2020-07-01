function [] = B(x)
    xr = reshape(x, 4, 4, 2);

    for i=1:size(xr, 3)
        xr(:, :, i) = xr(:, :, i)';
    end

    for k=1:size(xr, 3)
        for i=1:size(xr, 1)
            if sum(xr(i,:,k)) == sum(xr(:, i, k))
                disp('Yes');
            else
                disp('No');
            end
        end
    end
end

%% 1
switch (A) {
    case 0             % A == 0
        do something
        break
    case 1             % A == 1
        do something
        break
    default
        do something    % else
}

%% 2
if A == 0
    do something
elseif A == 1
    do something
else
    do something
